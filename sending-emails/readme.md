# Sending Emails

Nearly every project needs to do this. Here, we're going to demonstrate:

- How to send emails
- How to view/test emails when in dev mode

The most popular Clojure library for sending emails seems to be [postal](https://github.com/drewr/postal).

## Sending emails

We'll be using [mailtrap](https://mailtrap.io/) as our SMTP server. It's a pretty sweet service that lets you see all emails your app sends without *actually* sending them along to the destination email addresses.

So, go ahead and create a mailtrap account.

Next, create `/config/dev/config.edn` and give it the proper values (see. `/config/example.edn` for the key/values we need to define).

Then, have a look at `/src/sending_emails/mailer.clj`, which is where we define a useful `send-email` function that sends via the configured SMTP server.

Finally, have a look at `/src/sending_emails/core.clj`, which is where we send our email.


## Templating

If our application is sending its own automated emails, we could use something like [hiccup](https://github.com/weavejester/hiccup) to generate the email content.

If we want to allow users to create their own email templates, we could roll our own fairly simply with regex and some [sanitization](https://github.com/alxlit/autoclave) or use something like [gel](https://github.com/sfx/gel) which gives us safe user templating.


## Previewing/testing emails

In development mode, it's useful to be able to actually *see* the emails you are generating.

That's where something like [mailtrap](https://mailtrap.io/) comes in handy (as mentioned earlier). It's a *real* SMTP server that doesn't actually send emails, but rather drops them into a nice little web UI so you can inspect them.

I also find Rails's [letter_opener](https://github.com/ryanb/letter_opener) to be a really nice way to view emails that are sent when in dev mode. It launches each sent email in a new browser tab.

Clojure doesn't have a `letter_opener` equivalent, but we can get pretty close with a few lines of code.

Something like this will launch an HTML file in FireFox on Mac:

```clj
(def rt (Runtime/getRuntime))
(.exec rt "open -a /Applications/Firefox.app ./example.html")
```

To make this work cross-platform, we could make the `"open -a /Applications/Firefox.app"` portion configurable. So, our steps to a working letter-opener solution would be as follows:

- Create a config entry like this `:letter-opener "open -a /Applications/Firefox.app"`
- Check for this entry in our `send-message` function
- If it exists, ignore the `:smtp` setting, and instead, generate a temporary HTML file with the content of the email
- Launch the temporary html file in the configured browser

If you look at `launch-email` in `./src/sending_emails/mailer.clj`, you can see a working example.
