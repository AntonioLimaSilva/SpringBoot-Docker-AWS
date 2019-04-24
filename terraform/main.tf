provider "aws" {
  version = "~> 1.38"
  shared_credentials_file = "~/.aws/credentians"
  profile = "terraform-aws"
  region = "us-east-1"
}