import csv
import json
import sys

with open(sys.argv[1]) as f:
    reader = csv.DictReader(f)
    rows = list(reader)

with open(sys.argv[2], 'w') as f:
    json.dump(rows, f)
