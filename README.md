# kunlaboratorium-aws-lambda-text2speech

This repository contains a number of sequential exercises to convert a textfile to an MP3-file using AWS Lambda, AWS S3 and AWS Polly.
 

## Exercise 1 - Explore the AWS console

An AWS account has been created for you and will be provided by the Kunlaboratorium hosts.

Go to [AWS console](console.aws.amazon.com) and login.

AWS offers a lot of services, grouped into topics like Storage, Compute, ...
Take a look around and see if you can find the EC2 service.


## Exercise 2 - Explore Polly

* Open the console and locate the Polly service
* Click on the `text-to-speech` section
* Fill in some text, choose a language and region, choose a voice
* Now you can listen to the speech or download an MP3 file


## Exercise 3 - Create a text file in S3

* Open the console and go to the S3 storage service.
* Create a bucket.
* Create a folder (e.g. `text`)
* Inside the folder, upload a `.txt` file containing some text.

Source: https://docs.aws.amazon.com/AmazonS3/latest/user-guide/what-is-s3.html


## Exercise 4 - Build the project

* Let's first review the codebase together...
* Run `./gradlew clean build` to build our project
* A zipfile containing our code is now created in `build/distributions`
* Later, this has to be uploaded to AWS Lambda...


## Exercise 5 - Create a Lambda function

* Open the console and go to the Lambda service.
* Create a function from scratch for the Java 8 runtime
* In the Handler field, fill in `be.kunlabora.kunlaboratorium.aws_text_to_speech.MyTextToSpeechFunction::handleRequest`
* Upload the zipfile to Lambda and click save
* Create an test event using the Àmazon S3 put` template
* In the test event, fill in the aws region, bucket name and object key, according to the text file you created earlier
* Save the test event and execute your Lambda by clicking `Test`
* Your Lambda should fail with an `Access denied` error...  


## Exercise 6 - Roles

Why does your Lambda fail with an Access Denied error?
Why can't one AWS service just call another one?

* A role has been created automatically for your Lambda function
* Open the role in AWS IAM and attach the `AmazonPollyReadOnlyAccess` and `AmazonS3FullAccess` policies
* Try your Lambda again... 


## Exercise 7 - S3 event

We want our Lambda to be triggered automatically whenever we upload a new text file to our bucket

* Upload a new file to your bucket; is your Lambda triggered?
* In the S3 console, go to your bucket and locate `Events in the properties section
* Create a bucket event for .txt files that are created in your bucket
* See if you can wire the event to your lambda function
* Now try to upload a new file again...


## Congratulations!