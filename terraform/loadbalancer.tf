resource "aws_alb" "alb" {
  name = "luclima-beerstore-alb"
  security_groups = ["${aws_security_group.allow_load_balancer.id}"]
  subnets = ["${flatten(chunklist(aws_subnet.public_subnet.*.id, 1))}"]

  enable_deletion_protection = false
}

resource "aws_alb_target_group" "alb_target" {
  name = "luclima-beerstore"
  vpc_id = "${aws_vpc.main.id}"
  port = 8080
  protocol = "HTTP"

  health_check {
    path = "/actuator/health"
    matcher = "200"
    interval = 60
    healthy_threshold = 2
    unhealthy_threshold = 2
    timeout = 10
  }
}

resource "aws_alb_target_group_attachment" "alb_group_attachment" {
  count = 3

  target_group_arn = "${aws_alb_target_group.alb_target.arn}"
  target_id = "${element(aws_instance.instances.*.id, count.index)}"
  port = 8080
}

resource "aws_alb_listener" "alb_listener" {
  load_balancer_arn = "${aws_alb.alb.arn}"
  port = 80
  protocol = "HTTP"

  "default_action" {
    type = "forward"
    target_group_arn = "${aws_alb_target_group.alb_target.arn}"
  }
}

output "" {
  value = "${aws_alb.alb.dns_name}"
}