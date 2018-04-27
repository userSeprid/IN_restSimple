create table line_object
(
  lineid              int auto_increment
    primary key,
  value               varchar(255) null,
  longest_word        varchar(255) null,
  shortest_word       varchar(255) null,
  average_word_length int          not null,
  container_name      varchar(255) null,
  line_length         int          not null
)