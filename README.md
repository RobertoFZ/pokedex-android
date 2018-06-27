
# Android Project  
  
Android Application that use [Pokeapi](https://pokeapi.co/) for retrieve the Pokemon list.  
  
## Features  
  
- The application allow the user to authenticate (That is static)  
    - Email: test@gmail  
    - Password: password  
- The application allow the user to log out.  
- If the user log in once, the next time not is necessary to authenticate again. Need log out for ask login again.
- The application show the Pokemon list. While the user scroll the list, the application loads the next Pokemons.

## Setting up environment

- Download Git repository (`git clone https://github.com/RobertoFZ/pokedex-android.git`).
- Open the project using Android Studio.
- Open the AVD Manager (Tools > Android > AVD Manager).
- Click on "*Create Virtual Device*".
- Select *Phone* and choose *Nexus 4* with API 25.
- Go to Run > Run 'App' and select the Virtual Device created