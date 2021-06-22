# GameBank

### Task Overview

- [x] Trending Games
- [x] Search
- [x] Favorite

### Known bugs:
- [ ] After fetching from api then reloading from database the games won't all be in the same order. This will cause a quick shift. Perhaps sorting after fetching from api can help.

### Running Instructions
- Add API keys to local.properties
  - CLIENT_ID, TOKEN

### APK
- [apk](game_bank.apk)

### Preview

|Trending Games |Search | Favorite|
--- | --- | ---
![Trending](trending.png)|![Search](search.png) |![Favorites](favorites.png)

### Possible Improvements
- [ ] Properly handle authorization token
- [ ] Currently only fetching from api once in trending then relying on database. This has many ways in which it can be improved like refreshing data at every launch.





