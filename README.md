# Daydream API

A spring boot microservice that uses an H2 database to allow for various crud operations on node like datastructures.


# How to Run

1. Install Java11
   1. Using Homebrew: `brew install openjdk@11`
   2. Otherwise, us the official OpenJdk install instructions for your operating system / package manager
2. Install Gradle
   1. Using Homebrew: `brew install gradle`
   2. Otherwise, us the official Gradle install instructions for your operating system / package manager
3. Download this project and navigate to the directory
4. Run `gradle clean build`
5. Run `gradle bootRun`

The api is now running at `localhost:8080`


## API
The api can accept all methods outlined by this specification:

PUT /block: Creates a new block and returns the block JSON

PUT /block/{Id}: Creates a new block under parent block {Id} and returns the block JSON

GET /block: Fetches all top level blocks (blocks with no parents)

GET /block/{Id}: Fetches a block by ID

POST /block/{Id}: Updates a blocks properties by Id

#### Notes
* The api can accept any object as a parameter so there is no limitation on what you can pass as a parameter.

