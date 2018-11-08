# -*- coding: utf-8 -*-
"""
Created on Mon Mar  5 12:36:34 2018

@author: aldos
"""
from flask import Flask, jsonify, request
import json
app = Flask(__name__)

with open('data.txt') as json_file:
    data = json.load(json_file)
    
@app.route('/', methods = ['GET'])
def test():
    return jsonify({'hello':'world'})

@app.route('/data', methods = ['GET'])
def returnAll():
    return jsonify({'Data': data})

@app.route('/data/actors', methods = ['GET'])
def filter_actor():
    name = request.args.get('name', default ='*', type = str)
    age = request.args.get('age', default ='*', type = int)
    filtered = []
        
    if name == '*' and age == '*':
        for a in data['Actors']:
            filtered.append(a['ActorName'])
        return jsonify({'Filtered' : filtered})
    
    if age != '*':
        for a in data['Actors']:
            if name.lower() not in a['ActorName'].lower():
                continue
            else:
                if int(a['ActorAge']) == age:
                    filtered.append(a['ActorName'])
                else:
                    continue
        return jsonify({'Filtered':filtered})
    
    for a in data['Actors']:
        if name.lower() not in a['ActorName'].lower():
            continue
        else:
            filtered.append(a['ActorName'])
    return jsonify({'Filtered':filtered, 'Status': '200 OK'})

@app.route('/data/movies', methods = ['GET'])
def filter_movie():
    title = request.args.get('name', default = '*', type = str)
    year = request.args.get('year', default ='*', type = int)
    filtered = []
    
    if title == '*' and year == '*':
        for m in data['Movies']:
            filtered.append(m['movieTitle'])        
        return jsonify({'Filtered' : filtered})
    
    if year != '*':
        for m in data['Movies']:
            if title.lower() not in m['movieTitle'].lower():
                continue
            else:
                if int(m['movieYear']) == year:
                    filtered.append(m['movieTitle'])
                else:
                    continue
        return jsonify({'Filtered':filtered})
    
    for m in data['Movies']:
        if title.lower() not in m['movieTitle'].lower():
            continue
        else:
            filtered.append(m['movieTitle'])
    return jsonify({'Filtered':filtered, 'Status': '200 OK'})

@app.route('/data/actors/<string:name>', methods = ['GET'])
def filter_actor_string(name):
    filtered = []
    name = name.replace("_", " ")
    for a in data['Actors']:
        if name.lower() != a['ActorName'].lower():
            continue
        else:
            filtered.append(a)
            break
        
    return jsonify({'Filtered':filtered, 'Status': '200 OK'})

@app.route('/data/movies/<string:name>', methods = ['GET'])
def filter_movie_string(name):
    filtered = []
    title = name.replace("_", " ")
    for m in data['Movies']:
        if title.lower() != m['movieTitle'].lower():
            continue
        else:
            filtered.append(m)
            break
        
    return jsonify({'Filtered':filtered, 'Status': '200 OK'})


@app.route('/data/actors/<string:name>', methods = ['PUT'])
def put_actor_string(name):
    filtered = []
    name = name.replace("_", " ")
    for a in data['Actors']:
        if name.lower() != a['ActorName'].lower():
             continue
        else:
             filtered.append(a)
             break
    
    try: 
        filtered[0]['Movies'] = request.json['Movies']
    except:
        pass
    try:    
        filtered[0]['ActorAge'] = request.json['ActorAge']
    except:
        pass
    try:
        filtered[0]['MovieUrl'] = request.json['MovieUrl']
    except:
        pass
    
    return jsonify({'Updated':filtered, 'Status': '201 Created'})

@app.route('/data/movies/<string:name>', methods = ['PUT'])
def put_movie_string(name):
    filtered = []
    title = name.replace("_", " ")
    for m in data['Movies']:
        if title.lower() != m['movieTitle'].lower():
             continue
        else:
             filtered.append(m)
             break
    try:    
        filtered[0]['movieYear'] = request.json['movieYear'] 
    except:
        pass
    try: 
        filtered[0]['movieGross'] = request.json['movieGross']
    except:
        pass
    try:    
        filtered[0]['ActorList'] = request.json['ActorList']
    except:
        pass
    try:
        filtered[0]['actorUrl'] = request.json['actorUrl']
    except:
        pass
    
    return jsonify({'Updated':filtered, 'Status': '201 Created'})

#{"ActorAge":"21","MovieUrl":["/wiki/Infinitywar", "/wiki/Panther"], "Movies":["Infinitywar", "Panther"]}
@app.route('/data/add_actor', methods=['POST'])
def add_actor():
    try:
        new_actor = {'ActorName':request.json['ActorName']}
    except:
        return jsonify({'Status':'400 Bad Request'})
    
    data['Actors'].append(new_actor)
    return jsonify({'Added': new_actor, 'Status': '201 Created'})

# {"ActorList": ["Aldo Sanjoto"], "actorUrl": ["/wiki/Aldo_Sanjoto"], "movieGross": "2000022", "movieTitle": "Panther", "movieYear": "2030"}
@app.route('/data/add_movie', methods=['POST'])
def add_movie():
    try:
        new_movie = {'movieTitle':request.json['movieTitle']}
    except:
        return jsonify({'Status':'400 Bad Request'})
    data['Movies'].append(new_movie)
    return jsonify({'Added': new_movie, 'Status': '201 Created'})

@app.route('/data/delete_actor/<string:name>', methods=['DELETE'])
def delete_actor(name):
    name = name.replace("_", " ")
    filtered = [a for a in data['Actors'] if a['ActorName'].lower() == name.lower()]
    if len(filtered) == 0:
        return jsonify({'Status':'400 Bad Request'})
    data['Actors'].remove(filtered[0])
    return jsonify({'Deleted': filtered, 'Status': '200 OK'})
    
@app.route('/data/delete_movie/<string:name>', methods=['DELETE'])
def delete_movie(name):
    title = name.replace("_", " ")
    filtered = [m for m in data['Movies'] if m['movieTitle'].lower() == title.lower()]
    if len(filtered) == 0:
        return jsonify({'Status':'400 Bad Request'})
    data['Movies'].remove(filtered[0])
    return jsonify({'Deleted': filtered, 'Status': '200 OK'})

if __name__ == '__main__':
    app.run(debug=True, port=8080)