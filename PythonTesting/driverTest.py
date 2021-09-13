import requests
import time
import random
from h3 import h3
start = time.time()

max_lat = 10.963984
max_long = 106.477055

min_lat = 10.699614
min_long = 106.579939
lat = 10.914423272064769
long = 106.53143716335402

res=10
hexAddr = '8a65b19aa0b7fff'
seat = [2,4,7]
choose_seat = random.choice(seat)
id = 42344

for _ in range(10):
    requests.get("http://127.0.0.1:8081/kafka/driver/publish",params={
            'id': id,
            'longitude': long,
            'latitude': lat,
            'seat': choose_seat,
            'hexAddr': hexAddr
    })