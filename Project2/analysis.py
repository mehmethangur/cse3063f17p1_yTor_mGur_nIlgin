import math
import operator
import csv
from os import path, listdir
from os.path import isfile, join
from subprocess import Popen, PIPE

from PIL import Image
import numpy as np
from docx import opendocx, getdocumenttext
from wordcloud import WordCloud
import matplotlib.pyplot as plt
from cStringIO import StringIO
from pdfminer.pdfinterp import PDFResourceManager, PDFPageInterpreter
from pdfminer.converter import TextConverter
from pdfminer.layout import LAParams
from pdfminer.pdfpage import PDFPage

from filtering import Filtering
from read_file import ReadFromFile
from word_freq import WordFreq
from write_file import WriteToFile

d = path.dirname(__file__)


def write_to_file(filename, freq):
	print "writing into %s" % filename
	write_file = WriteToFile()
	write_file.write_to_csv(filename=filename, freq=freq)


def clear_text(text):
	filtering = Filtering()
	return filtering.get_feature_vector(filtering.process_text(text))


if __name__ == '__main__':
	print "starting program...."
	doc_path = raw_input("please give documents path (example: /Users/yasintoy/Desktop/LastPython/articles) : ")
	doc_path = doc_path + "/" if not doc_path.endswith("/") else doc_path
	files = [f for f in listdir(doc_path) if isfile(join(doc_path, f)) and f.split(".")[1] in ["txt", "doc", "docx", "pdf"]]
	if not files:
		print "Didn't find files to analysis"
		exit(-1) 
	get_documents =  ReadFromFile()
	word_freq = WordFreq()
	# initialize file based store
	for file in files:
		word_freq.frequent_file[file] = {}
	# redirect according to their file extension
	for file in files:
		print "reading from %s" % file
		filename, extension = tuple(file.split("."))
		if extension == "txt":
			text = get_documents.get_text_from_txt(doc_path + file)
		elif extension == "pdf":
			text = get_documents.convert_pdf_to_text(doc_path + file)
		elif extension == "docx":
			text = get_documents.get_text_from_docx(doc_path + file)
		else: # doc
			text = get_documents.get_text_from_txt(doc_path + file)

		word_freq.most_frequents(clear_text(text), file)
	word_freq.calculate_tf(files)	
	word_freq.calculate_tf_idf(files)

	freq = sorted(word_freq.frequent.items(), key=operator.itemgetter(1), reverse=True)[:50]
	tf_freq = sorted(word_freq.tf_words.items(), key=operator.itemgetter(1), reverse=True)[:50]
	tf_idf_freq = sorted(word_freq.tf_idf.items(), key=operator.itemgetter(1), reverse=True)[:50]
	# last step 
	write_to_file("tf_list.csv", tf_freq)
	write_to_file("tf_idf_list.csv", tf_idf_freq)
	word_freq.tf_word_cloud(tf_freq)
	word_freq.tf_idf_word_cloud(tf_idf_freq)

	for i, file in enumerate(files):
		print("Top words in document {}".format(i + 1))
		scores = {word: word_freq.tfidf(word, file, files, freq) for word in word_freq.frequent_file[file].keys()}
		sorted_words = sorted(scores.items(), key=lambda x: x[1], reverse=True)
		for word, score in sorted_words[:10]:
			print("\tWord: {}, TF-IDF: {}".format(word, round(score, 5)))
