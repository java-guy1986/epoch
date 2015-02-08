package com.desksoft.epoch.core.json;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SerializationExampleTreeModel {
	public static void main(String[] args) throws IOException {
		// Create the node factory that gives us nodes.
		JsonNodeFactory factory = new JsonNodeFactory(false);

		// create a json factory to write the treenode as json. for the example
		// we just write to console
		JsonFactory jsonFactory = new JsonFactory();
		JsonGenerator generator = jsonFactory.createGenerator(System.out);
		ObjectMapper mapper = new ObjectMapper();
		
		// the root node - album
		ObjectNode album = factory.objectNode();
		
		album.put("Album-Title", "Kind Of Blue");
		
		ArrayNode links = factory.arrayNode();
		links.add("link1").add("link2");
		album.put("links", links);

		ObjectNode artist = factory.objectNode();
		artist.put("Artist-Name", "Miles Davis");
		artist.put("birthDate", "26 May 1926");
		album.put("artist", artist);
		
		ObjectNode musicians = factory.objectNode();
		musicians.put("Julian Adderley", "Alto Saxophone");
		musicians.put("Miles Davis", "Trumpet, Band leader");
		album.put("musicians", musicians);
		
		mapper.writeTree(generator, album);

	}

}
