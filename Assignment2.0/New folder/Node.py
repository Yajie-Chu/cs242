# -*- coding: utf-8 -*-
"""

@author: aldos
"""

class Node(object):
    def __init__(self, data):
        self.degreeIn = 0
        self.degreeOut = 0
        self.edges_in = []
        self.edges_out = []
        self.node_data = data
