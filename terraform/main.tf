provider "aws" {
  version = "~> 1.38"
  shared_credentials_file = "~/.aws/credentians"
  profile = "terraform-dev"
  region = "us-east-1"
}