# Text Analyzer
#### The application takes all words from the text and calculates their occurrences and positions

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/kanthakdominik/TextAnalyzer.git
```

**2. Run the app using maven**

```bash
mvn spring-boot:run
```
The app will start running at <http://localhost:8080>

**2. Test the app using curl**

```bash
curl -d "@data.json" -H "Content-Type: application/json" -X POST localhost:8080/api/texts/analyze
```

The file **data.json** is located in the main directory of the project

## Rest APIs
The app defines following CRUD APIs.

### Texts

| Method | Url                | Decription   | Sample Request Body | 
| ------ |--------------------|--------------|---------------------|
| POST   | /api/texts/analyze | Analyze text | [JSON](#analyze)    |


### Sample JSON Request Body

##### <a id="analyze">Analyze Text -> /api/texts/analyze</a>
```json
{
	"tekst": "word1, word2; word3. word4-word2? (word1) ** word1 /\\ word2+word3:word2!word2"
}
```

### Sample JSON Response Body

```json
[
  {
    "slowo": "word1",
    "powtorzenia": 3,
    "pozycje": [0, 5, 6]
  },
  {
    "slowo": "word2",
    "powtorzenia": 4,
    "pozycje": [1, 7, 9, 10]
  },
  {
    "slowo": "word2?",
    "powtorzenia": 1,
    "pozycje": [4]
  },
  {
    "slowo": "word3",
    "powtorzenia": 2,
    "pozycje": [2, 8]
  },
  {
    "slowo": "word4",
    "powtorzenia": 1,
    "pozycje": [3]
  }
]
```