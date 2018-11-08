# -*- coding: utf-8 -*-
"""
@author: aldos
"""

from Node import Node
import heapq

class Graph(object):
    def __init__(self):
        self.nodelist = []
        self.edgelist = []
       
    '''
    Function that return nodelist
    ''' 
    def get_node(self):
        return self.nodelist
    
    '''
    Function that add the nodes
    ''' 
    def add_node(self, node):
        if node in self.nodelist:
            return
        else:
            self.nodelist.append(node)
    
    
    '''
    Function that add the edges
    ''' 
    def add_edge(self, m, al, weight):
        edge = (m, al, weight)
        for nl in self.nodelist:
            try:
                if nl.node_data['ActorName'] == al:
                    self.edgelist.append(edge)
            except:
                pass
            
    '''
    Function that prints the list of movie an actor has worked in
    @param: actor name
    '''     
    def print_movie_list(self, actor_name):
        
        for nl in self.nodelist:
            try:                
                if nl.node_data['ActorName'].lower() == actor_name:
                    for ml in nl.node_data['Movies']:
                        print(ml)
                    return
            except:
                pass
    '''
    Function that prints the edges
    '''        
    def print_edgelist(self):
        print(len(self.edgelist))
        for el in self.edgelist:
            print(el)
    '''
    Function that prints the actor worked in a movie
    @param: movie name
    '''        
    def print_actor_name(self, movie_name):
    
        for nl in self.nodelist:
            try:
                if nl.node_data['movieTitle'].lower() == movie_name:
                    for al in nl.node_data['ActorList']:
                        print(al)
                    return
            except:
                pass

    '''
    Function that prints the movie gross
    @param: movie name
    '''     
    def print_a_movie_grossed(self, movie_name):
        for nl in self.nodelist:
            try:
                if nl.node_data['movieTitle'].lower() == movie_name:
                    print(nl.node_data['movieGross'])
                    break;
            except:
                pass
            
    '''
    Function that prints the oldest X actor  
    @param: index - the X
    '''           
    def print_oldest_actor(self, index):
        
        ages = []
        for nl in self.nodelist:
            try:
                if int(nl.node_data['ActorAge']):
                    ages.append(int(nl.node_data['ActorAge']))
            except:
                pass
        ages = heapq.nlargest(index, ages)
        
        i = 0
        for nl in self.nodelist:
            if i == index:
                return
            try:
                if int(nl.node_data['ActorAge']) in ages:
                    print(nl.node_data['ActorName'] + " age is " + nl.node_data['ActorAge'])
                    i = i + 1
            except:
                pass
    
    '''
    Returns the list of movie in a given year
    @param: year - Given year
    @return: movie list
    '''  
    def print_all_movies_inayear(self, year):
        movielist = []
        for nl in self.nodelist:
            try:
                if int(nl.node_data['movieYear']) == year:
                    movielist.append(nl.node_data['movieTitle'])
            except:
                pass
            
        return movielist
    
    '''
    Function that prints the list of actor in a given year
    @param: year - Given year
    '''          
    def print_all_actors_inayear(self, year):
        
        for nl in self.nodelist:
            try:
                if int(nl.node_data['movieYear']) == year:
                    for al in nl.node_data['ActorList']:
                        print(al)
            except:
                pass