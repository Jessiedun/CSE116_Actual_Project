import json
import socket
from threading import Thread
import csv

from flask import Flask, send_from_directory, request
from flask_socketio import SocketIO

import eventlet

eventlet.monkey_patch()

app = Flask(__name__)
socket_server = SocketIO(app)

scala_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
scala_socket.connect(('localhost', 8000))

delimiter = "~"


def listen_to_scala(the_socket):
    buffer = ""
    while True:
        buffer += the_socket.recv(1024).decode()
        while delimiter in buffer:
            message = buffer[:buffer.find(delimiter)]
            buffer = buffer[buffer.find(delimiter) + 1:]
            get_from_scala(message)


def get_from_scala(data):
    socket_server.emit('gameState', data, broadcast=True)


def send_to_scala(data):
    scala_socket.sendall((json.dumps(data) + delimiter).encode())


Thread(target=listen_to_scala, args=(scala_socket,)).start()


@app.route('/')
def index():
    return send_from_directory("index.html", 'index.html')


@app.route('/helloworld')
def helloworld():
    return "Hello world!"


@app.route("/whoseplaying") # 127.0.0.1/whoseplaying
def readpoints():
    with open('savefile.txt') as f:
        for line in f:
            print(line)
            #in console


@app.route('/setpoints')
def setpoints():
    # After game is played ajax request to this endpoint
    # Query string is passed with their data
    # Store data in csv file
    # Return string to signify it has been added
    username = request.query['username']
    points = request.query['points']
    file = open('savefile.csv','a')
    csvwriter = csv.writer(file)
    csvwriter.writerow([username,points])
    return "Added {} username with {} points".format(username,points)


@app.route('/points')
def points():
    # Return string with peoples usernames and points
    file = open('savefile.csv','r')
    combinedstring = ''
    for i in file:
        combinedstring+=i
    return json.dumps(combinedstring)






socket_server.run(app, port=8080)