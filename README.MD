# Spring-React-Postgres Skeleton

This is my graduate school (2019 ish) full-stack setup: 
- Postgres for my database
- Spring MVC for my server
- React for my front-end/UI

I made the skeleton so I could quickly whip up some lit tools without having to go through the setup process again.

# Maeda, this seems like a solid skeleton! How do I use it to also make lit tools?

Make sure you install Postgres with:
```
sudo apt-get install postgresql
```

Load the source code into Intellij as a Maven project.

Run the spring server, and it will run on port 8080.

Also run the front-end side under `src/main/resources/mergecsv-app`:
```
yarn start
```

And will run on port 5000.



