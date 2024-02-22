We can create a simple Java program that interacts with MongoDB installed on the operating system without involving Maven. This program will be a basic "Pokedex" application for first-term students. It will include everything from connecting to MongoDB, defining the Pokémon model, and implementing basic CRUD operations.

Before running this program, make sure MongoDB is installed and running on your system at mongodb://localhost:27017.

https://www.mongodb.com/try/download/drivers/java 

https://docs.mongodb.com/drivers/java

https://www.mongodb.com/community/forums/t/connecting-to-my-database-with-java/8348


import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import com.mongodb.client.FindIterable;

public class PokedexApp {

    // MongoDB Connection String
    private static final String CONNECTION_STRING = "mongodb://localhost:27017";

    // Pokémon Class
    public static class Pokemon {
        private String name;
        private String type;
        private int level;

        public Pokemon(String name, String type, int level) {
            this.name = name;
            this.type = type;
            this.level = level;
        }

        // Getters
        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public int getLevel() {
            return level;
        }
    }

    // MongoDB Connection Method
    public static MongoDatabase connectToMongoDB() {
        MongoClient mongoClient = MongoClients.create(CONNECTION_STRING);
        return mongoClient.getDatabase("pokedexDB");
    }

    // Method to Add a Pokémon to the Database
    public static void addPokemon(Pokemon pokemon) {
        MongoDatabase database = connectToMongoDB();
        MongoCollection<Document> collection = database.getCollection("pokemons");
        Document doc = new Document("name", pokemon.getName())
                           .append("type", pokemon.getType())
                           .append("level", pokemon.getLevel());
        collection.insertOne(doc);
    }

    // Method to Retrieve All Pokémon Data
    public static void getAllPokemons() {
        MongoDatabase database = connectToMongoDB();
        MongoCollection<Document> collection = database.getCollection("pokemons");
        FindIterable<Document> pokemons = collection.find();
        for (Document pokemon : pokemons) {
            System.out.println(pokemon.toJson());
        }
    }

    // Main Method to Test the Application
    public static void main(String[] args) {
        Pokemon pikachu = new Pokemon("Pikachu", "Electric", 5);
        Pokemon charmander = new Pokemon("Charmander", "Fire", 4);

        addPokemon(pikachu);
        addPokemon(charmander);

        getAllPokemons();
    }
}

Instructions for Running the Program:
Install MongoDB Java Driver: You need to have the MongoDB Java driver available for this program to work.
If you are not using a build tool like Maven, download the MongoDB Java driver .jar file from the MongoDB website and include it in your project's build path.

Compile and Run: Compile and run the PokedexApp.java file in your Java environment.

This program creates a simple Pokedex application where you can add Pokémon and retrieve the list of added Pokémon. 
The program uses the MongoDB Java driver to interact with a MongoDB instance running at mongodb://localhost:27017.


Error message: "package com.mongodb.client does not exist," 

The MongoDB Java driver is not correctly included in your project's classpath. 

Since you're using IntelliJ IDEA, you'll need to manually add the MongoDB Java driver to your project. 

Download MongoDB Java Driver:

Visit the MongoDB Java Driver page.
Download the latest version of the MongoDB Java driver (a .jar file).
Add the MongoDB Java Driver to Your IntelliJ Project:

Open your IntelliJ IDEA and navigate to your PokedexApp project.
Go to File > Project Structure (CTRL + ALT + SHIFT + S on Windows).
In the Project Structure dialog, select Libraries.
Click the + sign at the top to add a new library.
Choose Java in the add library dialog.
Navigate to the location where you downloaded the MongoDB Java driver .jar file, select it, and click OK.
Apply the changes and close the Project Structure dialog.
Rebuild the Project:

After adding the library, rebuild your project in IntelliJ IDEA.
Go to Build > Rebuild Project.
Once you've added the MongoDB Java driver to your classpath and rebuilt the project, the error should be resolved, and you should be able to run your PokedexApp program successfully.

Remember, the MongoDB server must be running on your machine for the application to connect and operate correctly.  
