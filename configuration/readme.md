# Configuring Clojure

When creating an application, you generally need it to have different configuration per environment. I prefer to store this information in a file per environment. In Clojure, there's a library that makes this approach quite simple:

https://github.com/yogthos/config

Using `yogthos/config`, we can write our config as plain ol' `.edn` files, with one file per environment. These files should be kept in a directory which is added to `.gitignore` to prevent accidentally committing sensitive config data/passwords.

I like to keep one file checked in, which is `config/example.edn`, which has all of the properties that we expect any environment's config to define, only with dummy values.

## Configuring a project

Follow these steps to get configuration working all swank like:

1. Add `[yogthos/config "0.8"]` to your project.clj `:dependencies`
2. Add the following `:profiles` to your project.clj

```clj
:prod {:resource-paths ["config/prod"]}
:dev  {:resource-paths ["config/dev"]}
:test  {:resource-paths ["config/test"]}
```

3. Add `/config/*/*.edn` to your `.gitignore`
4. Create `./config/example.edn` and set up your project's example config file
5. `cp ./config/example.edn ./config/dev/config.edn` and set up your dev properties
6. `cp ./config/example.edn ./config/prod/config.edn` and set up your prod properties
7. `cp ./config/example.edn ./config/test/config.edn` and set up your test properties
8. Run `lein run` to use the dev environment configuration
9. Run `lein with-profile prod run` to use the prod environment configuration
10. Run `lein test` to consume the test enviroment configuration
