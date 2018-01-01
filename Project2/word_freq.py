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

class WordFreq():
	"""docstring for WordFreq"""
	def __init__(self):
		self.frequent = {}
		self.frequent_file = {}
		self.tf_words = {}
		self.tf_idf = {}

	def _convert_into_word_cloud(self, freq, filename):
		key_array = [key for (key, value) in freq]
		wordcloud = WordCloud().generate(" ".join(key_array))
		wordcloud.to_file(path.join(filename))

	def most_frequents(self, text, filename):
		"""
			tf_list.csv
			Most frequent 50 words in the input set of documents, sorted descending
		by their term frequency (tf) coupled with their tf values (comma seperated file,
		example: document;7)
		"""
		for word in text:
			# total based store
			self.frequent[word] = self.frequent.get(word, 0) + 1
			# file based store
			self.frequent_file[filename][word] = self.frequent_file[filename].get(word, 0) + 1
		return self.frequent

	def calculate_tf(self, files):
		for file in files:
			for word in self.frequent_file[file]:
				calc = (self.frequent_file[file][word] / float(len(self.frequent_file[file].keys())))
				self.tf_words[word] = self.tf_words.get(word, calc) + calc

	def calculate_tf_idf(self, files):
		for file in files:
			for word in self.frequent_file[file]:
				calc = (self.tf_words[word] * self.idf(word, files))
				self.tf_idf[word] = self.tf_idf.get(word, calc) + calc
	
	def tf_word_cloud(self, freq):
		"""
			tf_wordCloud.pdf
			Word cloud of the these words
		"""
		self._convert_into_word_cloud(freq, "tf_wordCloud.pdf")

	def tf_idf_word_cloud(self, freq):
		"""
			tf_wordCloud.pdf
			Word cloud of the these words
		"""
		self._convert_into_word_cloud(freq, "tf-idf_wordCloud.pdf")

	def tf(self, word, filename, freq):
		return self.frequent_file[filename][word] /  float(freq[0][1])

	def n_containing(self, word, document_list):
		return sum(1 for filename in document_list if word in self.frequent_file[filename].keys())

	def idf(self, word, document_list):
		return abs(math.log(len(document_list) / float((1 + self.n_containing(word, document_list)))))

	def tfidf(self, word, filename, document_list, freq):
		return self.tf(word, filename, freq) * self.idf(word, document_list)