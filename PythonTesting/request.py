import requests
import time

start = time.time()
response=requests.post('https://localhost:8081/kafka/driver/publish')
print("Execution time: ", time.time()-start)