import os
import pickle

import preprocessing as preprocessing
from flask import Flask, request
from sklearn import preprocessing


app = Flask(__name__)
salary_model = pickle.load(open("model.pickle","rb"))

@app.route('/predict', methods = ['POST'])
def salaryPredict():
    server_input = request.get_json() #gets the values from the app
    input_list = list(server_input.values()) #makes input into a list
    label_encoder = preprocessing.LabelEncoder()
    input_list = label_encoder.fit_transform(input_list)  # label encoding the list
    predict = salary_model.predict([input_list]).tolist #predicts model
    return predict, 200 #tells us everything works


if __name__ == '__main__':
    app.run(port = int(os.getenv("http://127.0.0.1", 8080)), debug = True)
