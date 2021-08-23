import requests
import time
import random

start = time.time()
# my_api_key = "AIzaSyAL0ca3F7rIMYEqOwzaRyERAI0Zpbbgwwo"

class PointCoordinate:
    def __init__(self, latitude, longitude):
        self.latitude = latitude
        self.longitude = longitude

    def __str__(self):
        return str(self.longitude) + "," + str(self.latitude)

def random_point(num_rand):
    counter = 0
    point_list = []
    max_lat = 10.963984
    max_long = 106.477055

    min_lat = 10.699614
    min_long = 106.579939
    f = open("coordinate.csv", "w+")
    f.close()

    with open("coordinate.csv", "w") as file:
        while counter != num_rand:
            lat = random.uniform(min_lat, max_lat)
            long = random.uniform(min_long, max_long)
            new_point = PointCoordinate(lat, long)
            point_list.append(new_point)
            file.write(str(new_point)+"\n")
            counter +=1

    return point_list

num_random = 100
list_point = random_point(num_random)

src = PointCoordinate(10.838980, 106.647141)

prepare_dest = ""
for index, point in enumerate(list_point):
    if index != len(list_point) -1:
        prepare_dest += str(point) + ";"
    else:
        prepare_dest += str(point)

print(prepare_dest)


response=requests.post('http://127.0.0.1:5000/table/v1/driving/'+str(src)+";"+prepare_dest+'?sources=0')
print(response.json())
print("Execution time: ", time.time()-start)