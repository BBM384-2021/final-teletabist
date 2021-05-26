<template>
  <div class="admin-bar">
      <b-container fluid class="p-0 m-0">
          <b-row class="p-0 m-0">
              <b-col cols="8">Clubby - Hobbies Done Right</b-col>
              <b-col cols="4">
                  <ul class="admin-tools">
                      <li><a href="/admin/users">users</a></li>
                      <li><a href="/admin/clubs">clubs</a></li>
                      <li><a href="/admin/tickets">tickets</a></li>
                  </ul>
              </b-col>
          </b-row>
      </b-container>
  </div>
</template>

<script>
export default {
    name: "AdminBar",
}
</script>

<style lang="scss" scoped>
    .admin-bar{
        width: 100%;
        height: 32px;
        background: $bar-color;
        color: $text-color;
        .container-fluid{
            height: 100%;
        }
    }
    .admin-tools{
        padding-right: 65px;
        float: right;
        text-align: right;

        >li{
            color: $text-color;
            margin-right: 20px;
            >a{
                color: $text-color;
                text-decoration: none;
                &:hover{
                    text-decoration: underline;
                }
            }
            display: inline-block;
        }
    }
</style>