Hello all

A payment card number, primary account number (PAN), or simply a card number, is the card
identifier found on payment cards. The PAN is a 16 digit number displayed on one side of the
card. The first 6 digits of a payment card number (credit cards, debit cards, etc.) are known as
the Issuer Identification Numbers (IIN), previously known as Bank Identification Number (BIN).
These identify the institution that issued the card to the card holder.

Given the following clearing cost matrix:

Card issuing country     Clearing Cost (USD)
      US                           $5
      GR                           $15
     Others                        $10
     
This is a demo REST API that can do the following:

- Has full Create Read Update Delete operations on the clearing cost matrix
- Can respond with the clearing cost of a given card number, utilizing the information
- A web (HTTP) API that can support the following action
  HTTP POST on /payment-cards-cost of the following JSON:
  {
  "card_number": <pan>
  }
 Response:
  {
  "country": <iso2_code>,
  "cost": <decimal>,
  }

- Comprehensive unit tests
- JWT token security

Documentation with full request-response examples is provided in Documentation.txt file

Thank you
