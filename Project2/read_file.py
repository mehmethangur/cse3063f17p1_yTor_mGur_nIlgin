import math
import operator
import csv

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


class ReadFromFile():

	def get_text_from_txt(self, filename):
		return open(filename).read()

	def get_text_from_docx(self, filename):
		""" 
			use doc module
			python doc 
		"""
		document = opendocx(filename)
		paratextlist = getdocumenttext(document)
		newparatextlist = [paratext.encode("utf-8") for paratext in paratextlist]
		return ' '.join(newparatextlist)

	def get_text_from_doc(self, filename):
		cmd = ['antiword', file_path]
		p = Popen(cmd, stdout=PIPE)
		stdout, stderr = p.communicate()
		return stdout.decode('ascii', 'ignore')

	def convert_pdf_to_text(self, fname, pages={}):
		""" 
			Read from pdf and convert into text
		"""
		output = StringIO()
		manager = PDFResourceManager()
		converter = TextConverter(manager, output, codec='utf-8', laparams=LAParams())
		interpreter = PDFPageInterpreter(manager, converter)

		infile = open(fname, 'rb')
		for page in PDFPage.get_pages(infile, pages):
			interpreter.process_page(page)
		infile.close()
		converter.close()
		text = output.getvalue()
		output.close
		return text 