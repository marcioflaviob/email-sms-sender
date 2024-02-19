
# Email and SMS Sender

An API that sends an email and SMS at the same time to someone. It uses abstraction to change from one email sender provider to another if the first one fails.


## API's used

 - [AWS Simple Email Service](https://aws.amazon.com/ses/)
 - [Spark Post](https://app.sparkpost.com/auth) _- in case AWS fails_
 - [Twilio](https://www.twilio.com/en-us) _- for SMS_


## API Documentation (How it works)

#### Sends the email and SMS

```http
  POST /api/send
```

| Payload   | Type       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `EmailSMSEntity` | `JSON` | **Required**. A JSON with the information to send the email and sms. |

#### Example of JSON payload

```
{
   "phone_number":"+33123456789",
   "subject":"A message for you:",
   "email":"dest_email@gmail.com",
   "message":"Hello World!"
}
```

#### Error handling

It automatically deals with errors of missing information on the payload.

When a `phone_number` is not informed it sends only the email, and vice versa.

When a `subject` is not informed it sends a default subject: `A message for you!`

When neither `phone_number` nor `email` are informed it throws an exception.
