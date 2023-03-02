# Shapes Photo Album 

## The Model
### Model Overview

The target of all the classes defined is to build an application that helps to create a simple photo album from shapes, using the classic **_Model-View-Controller_** architecture. The focuses at this stage is on the **Model** part.

### Model Design

The whole Model is based on **three connected packages**. Different pages have its own focus so as to achieve "Speration of Concerns" principle.

#### 1. `shape`
**This package defines all the features and elements to form and transform a shape.**
  - **Interface**: `IShape`
    - Seves as the contract and defines the methods that all the concrete shape classes should have.
    - The methods defined enables the clients to generate the shape with a name, change the shape color, change shape position, as well as re-scale the shape.
  - **Abstract Class:** `AbstractShape`
    - Implement the IShape interface and defines the default methods implementation to avoid code reuse.
  - **Concrete Classes:** `Rectangle`, `Oval` 
    - Concrete classes extended from the AbstractShape class.
    - Defines specific methods works solely for that shape.
  - **Other Helper Classes:**
    - `Color`: defines the Color features which are applicable to any shapes.
    - `Point2D`: defines the position of the shape.
#### 2. `album`
  - **Interface**: `IAblum`
    - Seves as the contract and defines the methods that all the inherited Album classes should have.
    - The methods defined enable the clients to:
      - Add a new shape into the album: the shape name need to **unique**
      - Remove an existing shape out of the album
      - Modify and transform the shape on the album (color, position, scale)
      - Take a snapshot to store the current state of all the shapes
      - Review all the snapshots' IDs
      - Review all the snapshots' details 
  - **Model:** `AlbumModel`
    - Inherit the IAlbum interface, which specifies the implemention of the methods defined in IAlbum.
    - Data Structure: The album model is constructed with a `LinkedHashMap` of shapes and a `LinkedHashMap` of snapshots. 
    - Using `LinkedHashMap` could store the shapes and snapshots with its unique identifier, retrive the shapes and snapshots at O(1) complexity. It also allows insertion-order iteration over the HashMap.
  - **Other Helper Classes:**
    - `Snapshot`: defines all the elements needed to form a Snapshot, including id, time stamp, description, as well as the current shapes in the album.
    - `ShapeNotFoundException`: a self-designed exception to be raised when the shape to be transform is not on the album. Useful when the client trying to remove/modift a shape which does not exist on the album

#### 3. `utils`      
  - `commonUtils`: defines several static methods which are repetively used in the above two packages, including: 
    - `invalidString`: check if the string is empty or contains only white space codepoints. Used to check names and snapshot description.
    - `invalidColorValue`: check if the color value is lower than 0 or lager than 255
    - `invalidScaleAttribute`: check if the scale attribute is negative

## UML 
![Shape-Album-UML](hw8-v4/resources/uml-model.png)


## The Controller

## The View



