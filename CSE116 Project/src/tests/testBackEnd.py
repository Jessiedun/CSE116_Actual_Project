from ..Code import backend
import unittest

class TestBackEnd(unittest.TestCase):
    def testing(self):
        playerNames = ["Justin", "Johnson", "Melinda"]
        x = "How many circles are there in the picture?"
        y = "How many squares are there in the picture?"
        z = "What was the first shape in the picture?"
        # qDict = {x: 1, y: 2, z: 1}
        # Johnson also worked on this unit testing with me, his laptop just died while we were working
        self.assertEqual(backend.getName(playerNames), ["Justin", "Johnson", "Melinda"])
        self.assertEqual(backend.checkPlayersAmountLeft(playerNames), 3)
        self.assertIn(backend.askQuestion(), [(x, 1), (y, 2), (z, 1)])
        # self.assertEqual(backend.askQuestion(), ('How many circles are there in the picture?', 1
        #                                         or ('What was the first shape in the picture?', 1
        #                                         or ('How many squares are there in the picture?', 2))))
