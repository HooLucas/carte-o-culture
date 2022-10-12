#############################
# DEFINITION DES LIBRAIRIES #
#############################

import pandas as pd

#############################
# DEFINITION DES FONCTIONS  #
#############################

#############################
#     PARTIE PRINCIPALE     #
#############################

df = pd.read_json("dataset_geoname.json")
data = []
for i in range(0, len(df)):
    v = df['fields'][i]
    if 'cou_name_en' in v.keys():
        if v['cou_name_en'] == 'France':
            x = {
                'name': v['name'],
                'lat' : v['coordinates'][0],
                'long': v['coordinates'][1]
            }
            data.append(x)
df_france = pd.DataFrame(data)
df_france.to_json('data_geo_france.json')

#############################
#           TESTS           #
#############################

#############################
#         REFERENCES        #
#############################

# A FAIRE
# [ ] Encoding nom
# [ ] Fonction extraire selon critère
# [ ] Test vitesse selon critères
# [ ] Looping plus rapide