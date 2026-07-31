# AI use journal

## Commit 1
- Asked AI's help to set up via SpringBoot as I am unfamiliar with Java's SpringBoot
-  Accepted AI's recommendation to use PostgreSQL which I agree as it is unnecessary to use others such as MongoDB/Redis
-  Successfully managed to run the java application locally

## Commit 2
- Asked AI on the relevant fields that should be included in my respective entities, filtered accordingly to project requirements before adding
- Engaged AI's assistance in setting up the project structure, such as the Entity, Enums and Repository folders

## Commit 3
- Started building on Claimant first as it was simple. Followed AI's guidelines on the Project overall architecture and structure such as using a Controller->Service->Repository->Database. Relevant fields required to create a Claimant passed into Request, used AI to ensure nothing is missing and give suggestions on what to include in the Request

## Commit 4
- Continued to build on Claims which Claimants can file. Once again, used AI to ensure nothing is missing, fields are appropriate, and all necessary fields are included. On a side note, AI recommended claimant to fill in estimated liability but I feel that should be filled up by a ClaimsOfficer instead of by the claimant himself. Changed accordingly and will implement ClaimsOfficer next.

## Commit 5
- Built on AssignClaims which assigns one Claim to one ClaimOfficer. Followed AI's guidance to change AssignClaimRequest.java, update Controller, Service and followed by ServiceImpl. I initially wondered why not also pass Officer's ID in the url, but seems like it is a good practice to only include id of what we are changing in the url, and other information in the request body
- Also went to set business logic such as once assigned, unable to assign claims again to a new officer
- Refactored error messages to look nicer rather on relying on default IllegalArgumentException