from kiteconnect import KiteConnect

kite = KiteConnect(api_key="ol0rnn4qxfcrn6vf")
print(kite.login_url())
token = input("Enter token:")
data = kite.generate_session(token, api_secret="vsc3vuop89k8ffqkcafdgwqpj2t2j6oa")
print(data["access_token"])
