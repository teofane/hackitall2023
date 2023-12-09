Pentru a fetchui toate evenimentele care vor urma -> http://localhost:8080/events GET
Pentru a fetchui un eveniment -> http://localhost:8080/events/{id} GET DAR nu e nevoie ca oricum aducem toate datele la primul fetch
Pentru a trimite input-ul userului din chat catre ChatGPT -> http://localhost:8080/chatgpt POST cu BODY - RAW - TEXT ex. i want to see events related to sunlight
Pentru a primi recomandare pentru un eveniment pentru utilizator -> http://localhost:8080/recommandetion GET intoarce EVENTUL cel mai potrivit
Pentru a fetchui evenimentele UTILIZATORULUI -> http://localhost:8080/user/events GET intoarce o lista