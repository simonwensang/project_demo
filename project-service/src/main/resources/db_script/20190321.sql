ALTER TABLE `wine_server`.`wm_drop`
ADD COLUMN `accuracy` VARCHAR(45) NULL COMMENT '精度' AFTER `is_off_line`,
ADD COLUMN `type` TINYINT NOT NULL DEFAULT 1 COMMENT '1无出酒模块2有出酒模块' AFTER `accuracy`;
