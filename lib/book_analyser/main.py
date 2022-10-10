# Défnition des librairies
import ebooklib
from ebooklib import epub
from bs4 import BeautifulSoup
import json
import re
import unicodedata

# Définition des fonctions 
def load_book(file_name):
    book = epub.read_epub("./lib/book_analyser/book/"+file_name)
    return book

def get_text(b):
    chapters = list(b.get_items_of_type(ebooklib.ITEM_DOCUMENT))
    texts = []
    for c in chapters:
        soup = BeautifulSoup(c.get_body_content(), 'lxml',)
        text = soup.get_text()
        #text = [para.get_text(strip=True) for para in soup.find_all('p')]
        #text = [para.normalize().get_text() for para in soup.find_all('p')]
        text = text.replace("\n",' ')
        text = text.replace("\xa0",' ')
        #text = text.replace("-",' ')  impossible Aix-en-provence
        text = re.sub('\[.[0-9]{0,4}\]', '', text)
        text = text.replace(".",'')
        text = text.replace("…",'')
        text = text.replace(",",'')
        texts.append(text)
    #texts = ' '.join(texts)
    #texts = texts.replace('\n', ' ')
    #texts = texts.replace('\u00e8', 'è')   
    texts = ' '.join(texts)
    return texts

def get_lieux(b):
    return "1"

def create_book_data(b):
    book = {
        'name'   : b.get_metadata('DC', 'title')[0][0],
        'author' : b.get_metadata('DC', 'creator')[0][0],
        'pages'  : get_text(b),
        'lieux'  : get_lieux(b)
    }
    return book

def detect_lieu(data):
    # un nom de lieu est débutté par une maj sur chaque partie ? Aix-en-Provence
    # stat
    # detection maj : 8300 result
    # detection maj suivie d'une minuscule : 7400
    # enlever double : 1838
    # nettoyer encoding : 1597
    # enlever mot commun : 966 si mot minuscule existe alors enlever ? Paris/paris 
    # enlever nom : https://www.back4app.com/database/back4app/list-of-names-dataset
    lieu = []
    for m in data['pages'].split(' '):
        if re.search('^[A-Z][a-z]', m):
            lieu.append(m)
    lieu = list(dict.fromkeys(lieu))
    lieu.sort()
    d = data['pages'].split(' ')
    i = 0
    while i < len(lieu):
        if lieu[i].lower() in d:
            lieu.pop(i)
            i=i-1
        i=i+1
    #print(lieu[0:200])
    #print(len(lieu))


b1 = load_book("fantine.epub")
d1 = create_book_data(b1)
detect_lieu(d1)

b2 = load_book("manon-lescaut.epub")
d2 = create_book_data(b2)
detect_lieu(d2)

data = d1

with open("./lib/book_analyser/book/data.json", "w") as write_file:
   json.dump(data, write_file)

# Références
# https://andrew-muller.medium.com/getting-text-from-epub-files-in-python-fbfe5df5c2da
# http://docs.sourcefabric.org/projects/ebooklib/en/latest/tutorial.html#reading-epub
