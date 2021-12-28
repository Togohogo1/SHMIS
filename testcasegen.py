'''
{"firstName":"John","lastName":"Smith","password":"password","appointments":[],"address":"123 Sesame Street","birthdate":"January 1, 1987","gender":"Male","telephone":"647-123-4567","id":"johnsmith_1","designation":"Patient","age":34,"email":"john.smith@fakemail.com"}
'''
import random
import string

for i in range(20):
    a = random.randint(3, 15)
    b = random.randint(3, 15)
    pre = ''.join(random.choice(string.ascii_letters) for i in range(a))
    suf = ''.join(random.choice(string.ascii_letters) for i in range(b))
    print(f'''"{i}":{{"firstName":"{pre}","lastName":"{suf}","password":"password","appointments":[],"address":"123 Sesame Street","birthdate":"January 1, 1987","gender":"Male","telephone":"647-123-4567","id":"_","designation":"Patient","age":34,"email":"{pre}.{suf}@fakemail.com"}},''')
