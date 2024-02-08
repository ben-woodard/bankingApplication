create database hibernate_assignment;
select * from users u
left join user_account ua on ua.user_id = u.user_id
left join accounts a on a.account_id = ua.account_id;

select * from address;

