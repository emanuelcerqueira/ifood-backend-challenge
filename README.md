# iFood Back-end Challenge

## How to run

##### 1. Clone the project
``
git clone https://github.com/emanuelcerqueira/ifood-backend-challenge.git
``

##### 2. Build the project
``
./mvnw package
``
##### 3. Get Spotify and OpenWheatherMap API keys and fill in the .env file
```
SPOTIFY_API_KEY=your_spotify_api_key
OPENWEATHERMAP_API_KEY=your_openweathermap_api_key
```

##### 4. Build and run image
``
docker-compose up --build
``

## Examples of API Calls
```
GET http://localhost:8080/api/playlist-suggestion/by-name?name=Portugal
```
```yaml
{
  "name": "90s Rock Anthems",
  "musicGenre": "Rock",
  "songs": [
    "Nirvana - Smells Like Teen Spirit",
    "Pearl Jam - Jeremy",
    "Rage Against The Machine - Killing In The Name",
    "Red Hot Chili Peppers - Under the Bridge",
    "The Cranberries - Zombie",
    "Soundgarden - Black Hole Sun",
    "Radiohead - High And Dry",
    "The Smashing Pumpkins - 1979 - Remastered 2012",
    "Nine Inch Nails - Closer",
    "Alice In Chains - No Excuses"
  ]
}
```
&nbsp;
```
GET http://localhost:8080/api/playlist-suggestion/by-name?name=Cuiaba
```
```yaml
{
  "name": "The Party Hits of the 2010s",
  "musicGenre": "Party",
  "songs": [
    "Young Thug - I Know There's Gonna Be (Good Times) [feat. Popcaan]",
    "Calvin Harris - Slide (feat. Frank Ocean & Migos)",
    "Kanye West - All Of The Lights",
    "Martin Garrix - Summer Days (feat. Macklemore & Patrick Stump of Fall Out Boy)",
    "SBTRKT - Wildfire",
    "Post Malone - Better Now",
    "Lizzo - Truth Hurts",
    "Chris Brown - Look At Me Now",
    "MEDUZA - Piece Of Your Heart",
    "Snakehips - All My Friends (feat. Tinashe & Chance the Rapper)"
  ]
}
```
&nbsp;
```
GET http://localhost:8080/api/playlist-suggestion/by-name?name=Salvador
```
```yaml
{
  "name": "Pop Brasil",
  "musicGenre": "Pop",
  "songs": [
    "Leo Santana - Século 21",
    "Anitta - Me Gusta (with Cardi B & Myke Towers)",
    "MC Kevinho - Avançada",
    "Luísa Sonza - TOMA",
    "Manu Gavassi - Deve ser horrível dormir sem mim",
    "Carol & Vitoria - Eu Tô Gostando de um Menino Aí",
    "Giulia Be - se essa vida fosse um filme",
    "Lagum - Será",
    "Vitão - CALIFORNIA / Citação: De Repente California",
    "Rashid - Pipa Voada (feat. Emicida)"
  ]
}
```
&nbsp;
```
GET http://localhost:8080/api/playlist-suggestion/by-name?name=London
```
```yaml
{
  "name": "Classical Essentials",
  "musicGenre": "Classical",
  "songs": [
    "Lang Lang - The Well-Tempered Clavier: Book 1, BWV 846-869: 1. Prelude in C Major, BWV 846",
    "Tasmin Little - The Lark Ascending",
    "George Frideric Handel - Handel / Orch. Hale: Keyboard Suite in D Minor, HWV 437: III. Sarabande",
    "Ludwig van Beethoven - Bagatelle No. 25 in A Minor, \"Für Elise\", WoO 59",
    "Sergei Rachmaninoff - Rhapsody On A Theme Of Paganini, Op.43: Variation 18. Andante cantabile",
    "Jacqueline du Pré - Cello Concerto in E Minor, Op.85 (1988 Digital Remaster): I. Adagio - Moderato",
    "Benjamin Grosvenor - Rhapsody In Blue",
    "Johann Sebastian Bach - Cello Suite No. 1 in G Major, BWV 1007: I. Prélude",
    "Nicola Benedetti - Meditation: Méditation from Thaïs",
    "Erik Satie - Gnossienne No. 1"
  ]
}
```
