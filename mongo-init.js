// mongodb 초기화 스크립트
db = db.getSiblingDB('chat');
db.createUser({
    user: 'shl',
    pwd: 'greedy',
    roles: [{ role: 'readWrite', db: 'chat' }]
});