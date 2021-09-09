import requests
import time
import random
from h3 import h3
start = time.time()

max_lat = 10.963984
max_long = 106.477055

min_lat = 10.699614
min_long = 106.579939
lat = random.uniform(min_lat, max_lat)
long = random.uniform(min_long, max_long)

res=10
hexAddr = h3.geo_to_h3(lat, long, res)
seat = [2,4,7]
choose_seat = random.choice(seat)

for _ in range(10000):
    requests.get("http://127.0.0.1:8081/kafka/user/publish",params={
        'longitude': long,
        'latitude': lat,
        'seat': choose_seat,
        'hexAddr': hexAddr
    })

