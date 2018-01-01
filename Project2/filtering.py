""" 
    filter = Filtering()
    processedTweet = filter.processTweet(i.text)
    featureVector  = filtering.getFeatureVector(processedTweet)

"""

import re

class Filtering():
    """
        This class for extracting unvaliable words
    """
    def __init__(self):
        self.stop_words = [word for word in open('stopwords.txt').read().split('\n')]

    def replace_two_or_more(self, s):
        #look for 2 or more repetitions of character and replace with the character itself
        pattern = re.compile(r"(.)\1{1,}", re.DOTALL)
        return pattern.sub(r"\1\1", s)

    def get_feature_vector(self, text):
        feature_vector = []
        #split text into words
        words = text.split()
        for w in words:
            #replace two or more with two occurrences
            w = self.replace_two_or_more(w)
            #strip punctuation
            w = w.strip('\'"?,.')
            #check if the word stats with an alphabet
            val = re.search(r"^[a-zA-Z][a-zA-Z0-9]*$", w)
            #ignore if it is a stop word
            if(w in self.stop_words or val is None):
                continue
            else:
                feature_vector.append(w.lower())
        return feature_vector

    def process_text(self,text):
        text = text.lower()
        #Convert www.* or https?://* to URL
        text = re.sub('((www\.[^\s]+)|(https?://[^\s]+))','URL',text)
        #Convert @username to AT_USER
        text = re.sub('@[^\s]+','AT_USER',text)
        #Remove additional white spaces
        text = re.sub('[\s]+', ' ', text)
        #Replace #word with word
        text = re.sub(r'#([^\s]+)', r'\1', text)
        text = text.strip('\'"')
        return text


