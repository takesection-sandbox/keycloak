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

# Access

`{KEYCLOAK BASE URL}/realms/{REALM NAME}/protocol/openid-connect/auth?response_type=code&redirect_uri=https://{REST API ID}.execute-api.{REGION}.amazonaws.com/{STAGE}/token&scope=email+openid&nonce=1&client_id=default`
