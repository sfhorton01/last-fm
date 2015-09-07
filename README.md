# last-fm

last-fm provides a simple practice rest web service that calls the last.fm apis to get a list of an artist's top ten albums
including track names for each album.

# To Run

This project utilizes Spring-Boot to create a runnable version of the web service in which you can pass the artist's name
on the command-line and the JSON output from the call will be logged to the console.

Use the command 'mvn spring-boot:run -Drun.arguments="<artist name>,<last.fm api key>"

The last argument is needed because the api account creation at last.fm has been disabled for over 2 weeks now.
