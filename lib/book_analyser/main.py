#############################
# DEFINITION DES LIBRAIRIES #
#############################

import ebooklib                 # Librairie pour manipuler des fichiers epub
from ebooklib import epub       # Classe pour acceuillir les données d'un fichier epub
from bs4 import BeautifulSoup   # Librairie pour extraire des infos de contenu HTML
import json                     # Librairie pour manipuler des fichiers json
import re                       # Librairie pour utiliser des expressions régulières (regex)
import unittest                 # Librairie pour effectuer des tests
import os                       # Librairie pour manipuler les chemin d'accès

#############################
# DEFINITION DES FONCTIONS  #
#############################

# Charge un ebook
def load_book(file_name):
    book = epub.read_epub("./book/"+file_name)
    return book

# Recupère le texte brut d'un ebook
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
        text = text.replace(".",'').replace("…",'').replace(",",'')
        texts.append(text)  
    texts = ' '.join(texts)
    return texts

# Identifie les lieux cité dans le livre
def get_lieu(data):
    # un nom de lieu est débutté par une maj sur chaque partie ? Aix-en-Provence
    lieu = []
    for m in data.split(' '):
        if re.search('^[A-Z][a-z]', m):
            lieu.append(m)
    lieu = list(dict.fromkeys(lieu))
    lieu.sort()
    d = data.split(' ')
    i = 0
    while i < len(lieu):
        if lieu[i].lower() in d:
            lieu.pop(i)
            i=i-1
        i=i+1
    return lieu

# Créé une structure de donnée contenant les informations d'un livre
def create_book_data(b):
    text = get_text(b)
    book = {
        'name'   : b.get_metadata('DC', 'title')[0][0],
        'author' : b.get_metadata('DC', 'creator')[0][0],
        'pages'  : text,
        'lieux'  : get_lieu(text)
    }
    return book

# Exporte une structure de donnée dans un fichier json
def export_data(data):
    with open("data.json", "w") as write_file:
        json.dump(data, write_file)

#############################
#     PARTIE PRINCIPALE     #
#############################

livres = os.listdir("./book/")
data = []
for l in livres:
    b = load_book(l)
    d = create_book_data(b)
    data.append(d)
    print(d['lieux'][0:100])
export_data(data)

#############################
#           TESTS           #
#############################

class TestStringMethods(unittest.TestCase):

    def test_is_not_lieux(self):
        self.assertTrue('Le'.lower(), 'le')

#############################
#         REFERENCES        #
#############################

# https://andrew-muller.medium.com/getting-text-from-epub-files-in-python-fbfe5df5c2da
# http://docs.sourcefabric.org/projects/ebooklib/en/latest/tutorial.html#reading-epub
# https://www.back4app.com/database/back4app/list-of-names-dataset