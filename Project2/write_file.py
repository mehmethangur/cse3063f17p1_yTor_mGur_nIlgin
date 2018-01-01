import csv

class WriteToFile():
	def write_to_csv(self, filename, freq):
		with open(filename, "w") as file:
			wr = csv.writer(file, quoting=csv.QUOTE_ALL)
			for (key, value) in freq:
				wr.writerow([key, value])
		return True