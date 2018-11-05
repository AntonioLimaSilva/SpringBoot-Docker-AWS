terraform {
  backend "s3" {
    bucket = "luclima23-terraform"
    key = "beerstore-treinamento-aws"
    region = "us-east-1"
    profile = "terraform"
  }
}