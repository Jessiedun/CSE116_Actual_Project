# import bottle
# from bottle import request
# import csv
# import json
#
#
# @bottle.route('/')
# def index():
#     return bottle.static_file("index.html", root = "")
#
#
# @bottle.route('/helloworld')
# def helloworld():
#     return "Hello world!"
#
#
# @bottle.route("/whoseplaying") # 127.0.0.1/whoseplaying
# def readpoints():
#     with open('savefile.txt') as f:
#         for line in f:
#             print(line)
#             #in console
#
#
# @bottle.route('/setpoints')
# def setpoints():
#     # After game is played ajax request to this endpoint
#     # Query string is passed with their data
#     # Store data in csv file
#     # Return string to signify it has been added
#     username = request.query['username']
#     points = request.query['points']
#     file = open('savefile.csv','a')
#     csvwriter = csv.writer(file)
#     csvwriter.writerow([username,points])
#     return "Added {} username with {} points".format(username,points)
#
#
# @bottle.route('/points')
# def points():
#     # Return string with peoples usernames and points
#     file = open('savefile.csv','r')
#     combinedstring = ''
#     for i in file:
#         combinedstring+=i
#     return json.dumps(combinedstring)
#
#
# bottle.run(host="localhost", port=8080, debug= True)