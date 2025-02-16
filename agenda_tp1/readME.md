

## Configuration du projet CalendarAPP dans Android Studio

CalendarAPP est une application mobile qui permet à l'utilisateur de consulter son agenda et de mettre en place des évenements sur les différentes dates qu'il souhaite.

### Étapes pour configurer le projet avec Android Studio :

1. Clonez le dépôt du projet en utilisant la commande suivante :
2. 

2. Lancez Android Studio et sélectionnez **"Open an existing project"**.

3. Naviguez vers le répertoire du projet cloné et ouvrez-le.

4. Laissez Android Studio télécharger les dépendances nécessaires et configurer le projet automatiquement.

5. Une fois la configuration terminée, cliquez sur **"Run"** pour exécuter l'application sur un émulateur ou un appareil physique.





# 📅 Application d'Agenda

## 📌 Fonctionnalités

- **Ajout d'événements** : Créez des événements avec une date et une heure spécifiques.
- **Consultation des événements** : Affichez une liste de tous les événements programmés.
- **Vue par jour, semaine, mois** : Naviguez facilement entre les vues quotidienne, hebdomadaire et mensuelle.
- **Mode sombre** : L'application s'adapte à vos préférences avec un mode sombre.

## 🎨 Interface Utilisateur

L'application utilise **Material 3** pour une interface moderne et fluide.

- **Écran d'accueil** : Vue d'ensemble du calendrier

                    ![](/home/flv/snap/marktext/9/.config/marktext/images/2025-02-17-00-04-47-image.png)

- **Navigation intuitive** : On peut naviguer facilement dans le calendrier grâce aux boutons prévus à cet effet .

- **Mode sombre** 

- **Affichage des événements** : Les événements sont affichés dans une liste .

                    ![](/home/flv/snap/marktext/9/.config/marktext/images/2025-02-17-00-05-16-image.png)

- **Ajout d'un événement** : Interface claire et simple pour ajouter de nouveaux événements.

                    ![](/home/flv/snap/marktext/9/.config/marktext/images/2025-02-17-00-06-02-image.png)

## 🛠️ Back-End

L'application utilise une gestion locale des événements, permettant une expérience fluide sans connexion internet nécessaire.

### # Explication du Code

- **Initialisation de la date sélectionnée** :
  
  - La date actuelle est récupérée avec `SimpleDateFormat` et assignée à la variable `selectedDate` lors du lancement de l'application. Le format utilisé est "dd/MM/yyyy".

- **Gestion de la sélection d'une date dans le calendrier** :
  
  - Le `CalendarView` permet à l'utilisateur de sélectionner une date. Lorsque la date change, la méthode `setOnDateChangeListener` est déclenchée et la variable `selectedDate` est mise à jour. La méthode `updateEventList()` est appelée pour afficher les événements de cette date.

- **Affichage des événements dans le RecyclerView** :
  
  - La `RecyclerView` est configurée avec un `LinearLayoutManager` et un `EventAdapter`. Elle affiche les événements associés à la date sélectionnée.
  - La méthode `updateEventList()` met à jour les événements dans le RecyclerView en récupérant la liste des événements pour la date sélectionnée. Si la liste est vide, un message "Il n'y a aucun événement" est affiché.

- **Ajout d'un événement** :
  
  - Un bouton flottant (`FloatingActionButton`) permet d'ouvrir une boîte de dialogue pour ajouter un événement.
  - La boîte de dialogue permet à l'utilisateur de saisir une description d'événement et de sélectionner une heure à l'aide d'un `MaterialTimePicker`.
  - Lorsque l'utilisateur ajoute un événement, la description et l'heure sont formatées et ajoutées à la liste des événements pour la date sélectionnée. La liste est ensuite mise à jour dans le `RecyclerView`.

- **XML utilisés** :
  
  - **`CalendarView`** : Utilisé pour permettre à l'utilisateur de sélectionner une date.
  - **`FloatingActionButton`** : Permet d'ouvrir la boîte de dialogue pour ajouter un événement.
  - **`RecyclerView`** : Affiche la liste des événements.
  - **`TextView`** : Affiche le message lorsqu'il n'y a pas d'événements pour la date sélectionnée.
  - **`AlertDialog`** : Utilisé pour afficher la boîte de dialogue permettant d'ajouter un événement.
  - **`MaterialTimePicker`** : Permet à l'utilisateur de sélectionner l'heure de l'événement.

---

## 🚀 Perspectives Futures

- **Intégration d'un calendrier synchronisé** pour se connecter avec d'autres services comme Google Calendar.
- **Partage d'événements** avec d'autres utilisateurs via des invitations.
- **Prise en charge des événements récurrents** pour faciliter la gestion d'événements réguliers.


