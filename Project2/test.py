import operator
import unittest

from os import path, listdir
from os.path import isfile, join


from write_file import WriteToFile
from filtering import Filtering
from word_freq import WordFreq
from analysis import clear_text


class TestToProject(unittest.TestCase):

	def test_get_file(self):
		write_file = WriteToFile()
		deneme_dict = {
			"deneme": 1,
			"bla": 2
		}
		freq = sorted(deneme_dict.items(), key=operator.itemgetter(1), reverse=True)
		result = write_file.write_to_csv(filename='deneme.csv', freq=freq)
		self.assertEqual(result, True)

	def test_process_text(self):
		filtering = Filtering()
		text = "WHAT'A    LOVELY    DAY"
		after_text = "what'a lovely day"
		result = filtering.process_text(text)
		self.assertEqual(after_text, result)

	def test_stopwords(self):
		text = "again MacOS win Neco"
		filtering = Filtering()
		result = filtering.get_feature_vector(filtering.process_text(text))
		self.assertNotIn("again", result)

	def test_find_files(self):
		doc_path = "/Users/yasintoy/Desktop/LastPython/articles"
		doc_path = doc_path + "/" if not doc_path.endswith("/") else doc_path
		files = [f for f in listdir(doc_path) if isfile(join(doc_path, f)) and f.split(".")[1] in ["txt", "doc", "docx", "pdf"]]
		self.assertIn("pd.pdf", files)
		self.assertIn("duman_2009.pdf", files)
		self.assertNotIn("neco.pdf", files)

	def test_word_freq(self):
		text = "bu bir deneme text deneme"
		word_freq = WordFreq()
		word_freq.frequent_file["deneme.txt"] = {}
		word_freq.most_frequents(clear_text(text), 'deneme.txt')
		self.assertEqual(word_freq.frequent_file["deneme.txt"]["deneme"], 2)

if __name__ == '__main__':
	unittest.main()
	