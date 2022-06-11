# android-app-kotlin-gitapp

Aplicación Kotlin Jetpack Compose para visualizar datos de Github dados un usuario y un token (Github token), implementa autenticación biométrica mediante huella dactilar.

![](https://camo.githubusercontent.com/5f8e3380acd174df4d50a0a775642065ba60f00c49dfbd5a496882705dfd98d7/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f2d4b6f746c696e2d3030393564353f7374796c653d666f722d7468652d6261646765266c6f676f3d6b6f746c696e266c6f676f436f6c6f723d666666)

### Implementación
La aplicación está escrita en Kotlin con Jetpack Compose, implementa el patrón de arquitectura **MVVM** y una estructura similar a lo que se especifica para **Clean Code**.

Se utiliza biometría como factor de autenticación posterior al registro del usuario, el registro se basa en colocar el username y un token de Github que debe tener al menos permisos de repositorio (carga de repositorios privados).

Los servicios que se consumen son los siguientes:

> Repositorios: https://api.github.com/search/repositories?q=user:{username}

> Commits: https://api.github.com/repos/{username}/{reponame}/commits

> Info de usuario: https://api.github.com/users/{username}

Se debe de enviar el token de usuario en el **HEADER** del request (Bearer ghp_XXXXXXXXXXXXXXXX...) para el field **Authorization**.

Para almacenar los datos de utiliza **UserDefaults** (usuario) y **Keychain** (github token).

### Como se ve?

![oie_2712214mXHJB9oR](https://user-images.githubusercontent.com/61942641/170595112-2e4240e1-3b00-4380-b902-a10fcbf32e0c.png)

### Importante

> Relacionado: https://github.com/hajc1294/ios-app-swift-gitapp.git

# TODO

* Pruebas unitarias
* Git worflow
