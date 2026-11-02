# Fil Rouge MAPD : simulateur de réseau de Petri (Groupe 5)

Mohamed Lemine Eleye, Ahmed Salem Elkhoumani (IMT Atlantique)

## Contenu

- `src/main/java/petrinet` : le code
    - `IPetriNet` (interface de service) et `PetriNet` (implémentation)
    - `Place`, `Transition`
    - `Arc` (abstraite), `InputArc` (abstraite), `SimpleArc`, `ZeroArc`, `EmptyingArc`, `OutputArc`
    - `Main` : exemple d'utilisation
- `src/test/java/petrinet` : les tests JUnit 5
    - `TestPlace`, `TestArc`, `TestTransition`, `TestPetriNet`, et `AllTests` (lance tous les tests)
- `docs` : comptes rendus et diagrammes

## Prérequis

Java 21 et Maven (intégré à IntelliJ IDEA).

## Lancer le code

Dans IntelliJ : ouvrir `pom.xml` comme projet, puis lancer `Main`.

## Lancer les tests

- Dans IntelliJ : lancer `AllTests`.
- En ligne de commande : `mvn test`

## Lien avec la conception

Le code suit le diagramme de classes du TD2/TD3, avec les noms traduits en anglais :

| Conception | Code |
|---|---|
| `IReseauPetri` / `ReseauPetri` | `IPetriNet` / `PetriNet` |
| `ArcSortant` (place vers transition) | `InputArc` |
| `ArcSimple`, `ArcZero`, `ArcVideur` | `SimpleArc`, `ZeroArc`, `EmptyingArc` |
| `ArcEntrant` (transition vers place) | `OutputArc` |
| `estTirable()`, `tirer()` | `isFireable()`, `fire()` |
| `estActif()`, `declencher()` | `isActive()`, `trigger()` |

En anglais, les arcs sont nommés du point de vue de la transition : un `InputArc` entre dans la transition, un `OutputArc` en sort.

Modifications par rapport au modèle :

- `Arc.getPlace()` : nécessaire pour vérifier l'exigence 3 et supprimer les arcs d'une place.
- `Transition` : ajout de `addInputArc()`, `addOutputArc()`, `removeArc()` et `removeArcsOf()`, utilisées par `PetriNet` pour gérer les arcs.
- `PetriNet` : ajout de `getPlaces()` et `getTransitions()`, utilisées par les tests.
- Les contraintes du modèle lèvent une exception : `IllegalArgumentException` (jetons négatifs, poids inférieur ou égal à 0, élément absent du réseau) et `IllegalStateException` (deux arcs dans le même sens).

## Stratégie de développement (question 4.1)

Stratégie ascendante : `Place`, puis les arcs, puis `Transition`, puis `PetriNet`. Chaque classe a été testée avant de passer à la suivante, en partant des petits réseaux de l'énoncé.