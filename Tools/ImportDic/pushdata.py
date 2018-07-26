import requests
i = 0
with open("UrlSave_small.txt", "r") as ins:
    for line in ins:
        print(requests.get(line))
        print(i)
        i=i+1
        
