import requests
import sys

url= 'http://127.0.0.1:8081/IgalNisanDanielNissim/'

def url_ok(url):
    r = requests.head(url)
    if r.status_code == 200:
        sys.exit(0)
    else:
        sys.exit(1)

url_ok(url)