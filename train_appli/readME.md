# 

## Configuration du projet SNCFApp dans Android Studio

SNCFApp est une application mobile qui permet à l'utilisateur de rechercher des trajets en fonction des gares et des dates saisies. Elle offre également la possibilité de consulter les billets achetés et de gérer le profil utilisateur.

### Étapes pour configurer le projet avec Android Studio :

1. Clonez le dépôt du projet en utilisant la commande suivante :

2. Lancez Android Studio et sélectionnez **"Open an existing project"**.

3. Naviguez vers le répertoire du projet cloné et ouvrez-le.

4. Laissez Android Studio télécharger les dépendances nécessaires et configurer le projet automatiquement.

5. Une fois la configuration terminée, cliquez sur **"Run"** pour exécuter l'application sur un émulateur ou un appareil physique.

# 📱 Application Mobile de Train 🚆

## 📌 Fonctionnalités

- **Consultation des horaires** : Accédez aux horaires des trains en temps réel.  

- **Gestion du panier** :  Possibilité de mettre des trajets de train dans le panier.

- **Filtrer TER TGV** : Possibilité de filtrer en fonction des TGV et TER.  

- **Page de Profil** : Une page de profil permettant de consulter nos informations .

- ## 🎨 Interface Utilisateur

L’application utilise **Material 3** pour une expérience utilisateur fluide et moderne.  

- **Écran d’accueil** : Page de recherche avec une champ pour la ville de Départ et un champ pour la ville d'arrivée .  

                      ![](/home/flv/snap/marktext/9/.config/marktext/images/2025-02-16-23-41-23-image.png)

- **Navigation intuitive** : Barre des tâches permettant de naviguer entre les différentes pages.  

                    ![](/home/flv/snap/marktext/9/.config/marktext/images/2025-02-16-23-40-58-image.png)

- **Mode sombre** : Afin de ne pas éblouir l'utilisateur si il l'utilise de nuit .  
- **Affichage de la liste des trains disponibles** : 

                    ![](/home/flv/snap/marktext/9/.config/marktext/images/2025-02-16-23-46-44-image.png)

- **Page de profil pour consulter ses informations**:

                    ![](/home/flv/snap/marktext/9/.config/marktext/images/2025-02-16-23-48-05-image.png)

- **Page de panier pour consulter les trajet ajoutés:**

                    ![](/home/flv/snap/marktext/9/.config/marktext/images/2025-02-16-23-49-06-image.png)

        

## 🛠️ Back-End

    Pour le back end j'ai utilisé des données en Dur permettant de simuler des recherches sur une application de trains . 
    
    ![](/home/flv/snap/marktext/9/.config/marktext/images/2025-02-16-23-40-28-image.png)

## Fonctionnement de l'affichage des trains selon les critères

L'objectif principal de cette activité est d'afficher une liste de trains filtrée en fonction des critères fournis par l'utilisateur, à savoir la ville de départ, la ville d'arrivée et la date. Voici un résumé détaillé de son fonctionnement :

### 1. Récupération des critères de recherche

Les critères de recherche (ville de départ, ville d’arrivée et date) sont récupérés à partir de l'Intent qui lance l'activité. Ces informations sont essentielles pour filtrer la liste des trains en fonction des préférences de l'utilisateur.

### 2. Filtrage des trains

Une liste pré-définie de trains est utilisée, chaque train étant caractérisé par plusieurs attributs : 

- Ville de départ
- Ville d’arrivée
- Heure de départ et d’arrivée
- Durée du trajet
- Prix
- Type de train (TGV, TER, etc.)
- Date du trajet

Après avoir récupéré les critères de recherche, la liste des trains est filtrée pour ne conserver que ceux dont les gares de départ et d’arrivée correspondent aux critères de l’utilisateur (sans tenir compte de la casse des caractères).

### 3. Affichage des trains

Une fois les trains filtrés, ceux-ci sont affichés dans un `RecyclerView`. Ce composant permet de gérer l'affichage d'une liste d'éléments dans une interface. Pour chaque train, un `Adapter` personnalisé, appelé `TrainAdapter`, est utilisé pour lier les données à l'interface graphique. Chaque train est alors affiché sous forme d'un élément de la liste avec ses informations principales (heure, gare, prix, etc.).

### 4. Interaction avec les éléments

L'utilisateur peut interagir avec la liste de trains en ajoutant des trajets à son panier. Chaque train dispose d'un bouton pour l'ajout au panier. Lorsqu'un utilisateur clique sur un train, ce dernier est ajouté à une liste `cartList`, et un message de confirmation est affiché via un `Toast`. Un autre bouton permet à l'utilisateur d'accéder à son panier pour visualiser les trajets sélectionnés.

### 5. Filtres supplémentaires

Deux boutons permettent à l'utilisateur de filtrer les trains par type : TGV ou TER. Lorsque l'utilisateur clique sur l'un de ces boutons, la liste des trains est mise à jour pour ne montrer que ceux du type choisi. Le `RecyclerView` est ensuite actualisé pour refléter ces nouveaux critères.

### 6. Interface XML

L'interface graphique repose principalement sur un `RecyclerView` pour afficher la liste des trains et plusieurs boutons (`ImageButton` et `Button`) pour interagir avec l'utilisateur. Le `RecyclerView` est configuré avec un `LinearLayoutManager`, permettant un affichage vertical de la liste des trains. Chaque élément de la liste est défini par un layout XML dans l'adaptateur, qui contient les informations nécessaires pour afficher un train (heure de départ, gare de départ, etc.).

### 7. Gestion du panier

  Le panier de l'utilisateur est géré sous forme d'une liste `cartList` qui contient les trains sélectionnés. Cette liste est mise à jour chaque fois qu'un utilisateur ajoute un train au panier. Le panier est accessible à tout moment via un bouton flottant (`fabCart`). En cliquant sur ce bouton, l'utilisateur est redirigé vers une autre activité ( `BasketActivity`) où il peut voir tous les trains qu'il a ajoutés à son panier.

Chaque ajout au panier est confirmé par un message `Toast` qui indique la ville de départ et la ville d'arrivée du train ajouté. Si l'utilisateur veut modifier ses choix, il peut simplement retourner à l'écran de recherche, ajouter ou supprimer des trains, et les modifications seront automatiquement mises à jour.

---

## 🚀 Perspectives Futures

- **Intégration d’un système de fidélité** avec des réductions sur les trajets fréquents.  
- **Compatibilité avec les cartes de transport** pour un accès rapide aux trains.  
