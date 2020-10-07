Keycloak
========

# Ansible

```
$ sudo -s
# echo "[web]" >> /etc/ansible/hosts
# echo "localhost" >> /etc/ansible/hosts
```

# Installation

```
$ cd deploy/playbook
$ ansible-playbook keycloak.yml
$ sudo /opt/keycloak/keycloak-11.0.2/bin/add-user-keycloak.sh -r master -u admin -p PASSWORD
```


